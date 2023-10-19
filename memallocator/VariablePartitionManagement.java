import java.util.Scanner;

class MemoryUnit{
    //内存单元的状态（0表示已使用 1表示未使用)
    public boolean state = false; 
}

class Memory{
    //表示内存大小
    public MemoryUnit[] memories;
    
    Memory(int n){
        memories = new MemoryUnit[n];
        for (int i = 0; i < n; i++){
            memories[i] = new MemoryUnit();
        }
    }
}

//采用最先使用算法对内存进行分配
class FirstFit{
    public Memory memory;
    public int mSize;
    public Job[] jobs;

    public FirstFit(int mSize, Memory memory, Job[] jobs){
        this.mSize = mSize;
        this.memory = memory;
        this.jobs = jobs;
    }
    
    //为job分配内存
    public void alloc(int jOrder){
        int i, j, k, count;
        //先遍历整个内存寻找最先适应的内存区域，将job装载进内存
        for (i = 0; i < mSize; i++){
            //找到未被占用的第一个内存地址，并计算从这个地址开始连续单元长度
            if(memory.memories[i].state == false){
            //判断大小是否可以装载进job
                count = 0;
                for (j = i; j < mSize; j++){
                    if(memory.memories[j].state == true){
                        break;
                    }
                    count++;  
                }

                if(count >= jobs[jOrder].len){
                    System.out.println("~~~~第" + jOrder + "个job分配成功!~~~~");
                    System.out.println();
                    jobs[jOrder].state = true;
                    jobs[jOrder].start = i;
                    //连续的内存单元长度大于job长度 可以装载进去
                    for(k = i; k < i + jobs[jOrder].len; k++){
                        memory.memories[k].state = true;
                    }
                    emptyMemory();
                }
                
                //i从j的地址开始继续循环
                i = j;
            }
        }

        if (jobs[jOrder].state == false){
            System.out.println(jOrder + "~~~~分配失败!~~~~");
        } 
    }

    //释放job所占用的内存
    public void back(int jOrder){
        int i, j, endLocation = 0;
        boolean flag = false;

        //回收的时候只能回收到下一个job的起始位置
        for(i = jobs[jOrder].start + 1; i < mSize; i++){
            endLocation = 0;
            for (j = 0; j < jobs.length; j++){
                if (jobs[j].start == i && j != jOrder){
                    endLocation = jobs[j].start--;
                    flag = true;
                    break;
                }   
            }
            
            if(flag == true){
                i = mSize;
            }
        }

        //判断endlocation是否超出当前job的地址范围
        if(endLocation > jobs[jOrder].start + jobs[jOrder].len - 1){
            endLocation = jobs[jOrder].start + jobs[jOrder].len;
        }

        for (i = jobs[jOrder].start; i < endLocation; i++){
            memory.memories[i].state = false;
        }
        System.out.println("~~~~回收成功~~~~");
        emptyMemory();
    }

    //打印出当前空闲的内存单元区域
    public void emptyMemory(){
        int i, j, k, count;

        System.out.println("<======空闲的内存区域======>");
        for(i = 0; i < mSize; i++){
            if (memory.memories[i].state == false){
                //从第一个未被占用的内存区域开始计算
                count = 0;
                for(j = i; j < mSize; j++){
                    if(memory.memories[j].state == true){
                        j = mSize;//设置循环条件，让循环体结束
                        break;
                    }
                    count++;
                }
                System.out.println("[" + i + "," + (i + count - 1) + "]");
                i = i + count;
            }
        } 
        System.out.println("<======空闲的内存区域======>");
        System.out.println();

       /* for(i = 0; i < mSize; i++){
            System.out.print(memory.memories[i].state);
        }*/
    }
}

class Job{
    public boolean state = false;
    public int len = 0;
    public int start;

    public Job(){

    }
}

public class VariablePartitionManagement{
    public int jNum, jSize, mSize, n, jOrder;
    public Job[] jobs;
    public FirstFit firstFit;
    Scanner in = new Scanner(System.in);

    public VariablePartitionManagement(){
        System.out.print("请输入创建的作业数：");
        jNum = in.nextInt();
        jobs = new Job[jNum];
        for(int i = 0; i < jNum; i++){
            jobs[i] = new Job();
        }

        System.out.println();
        System.out.println("请依次输入每个job占用的内存大小");
        System.out.println();

        for (int i = 0; i < jNum; i++){
            System.out.print("第" + i + "个job占用的内存大小为：");
            jSize = in.nextInt();
            jobs[i].len = jSize;
            jobs[i].state = false;
        }

        System.out.println();
        System.out.print("请设置内存的大小：");
        mSize = in.nextInt();
        Memory memory = new Memory(mSize);
        firstFit = new FirstFit(mSize, memory, jobs);
        printMainMenu();
    }

    //打印主操作界面
    public void printMainMenu(){
         do{
            System.out.println("<==============================>");
            System.out.println("*          0-分配内存          *");
            System.out.println("*          1-回收内存          *");
            System.out.println("*          2-退出              *");
            System.out.println("<==============================>");

            n = in.nextInt();
            switch(n){
                case 0:
                    System.out.println("请选择需要装载进内存的job序号：");
                    jOrder = in.nextInt();
                    if (jOrder < jNum){
                        if(jobs[jOrder].state == true){
                            System.out.println("~~~~第" + jOrder + "个job已经分配过！~~~~");
                        }else{
                            jobs[jOrder].state = true;
                            firstFit.alloc(jOrder);
                        }
                    }else{
                        System.out.println("输入有误，请重新输入！");
                    }
                    break;
                case 1:
                    System.out.println("请选择需要从内存中回收的job序号：");
                    jOrder = in.nextInt();
                    firstFit.back(jOrder);
                    break;
                case 2:

            }
        }while(n != 2);
    }

    public static void main(String[] args){
        VariablePartitionManagement vpm = new VariablePartitionManagement();
    }
}