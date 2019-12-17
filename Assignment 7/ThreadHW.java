import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
// Lavanya Verma(2018155)
//Ap assignment 7 
// Monsoon Semester IIITD
class ThreadHW{
    public static void main(String[] args) {
        
        Scanner c = new Scanner(System.in);
        Jar container = new Jar(c);
        
        Producer p = new Producer(container);        
        System.out.println("Enter Number of Consumer Threads to generate: ");
        int n = c.nextInt();
        (new Thread(p)).start();
        for(int x  = 0 ;x <n ;x++){
            (new Thread(new Consumer(container))).start();
        }
        
        
    }

}

class Node1{
    private long data ;
    private int term;
    private long computationTime;
    Node1 (long data , int term , long computationTime){
        this.data = data;
        this.term = term;
        this.computationTime = computationTime;
    }
    public long getData() { return data;}
    public int getTerm(){ return  term;}
    public long getTime(){ return computationTime;}
}




class Producer implements Runnable{
    private Jar j ;
    public Producer(Jar j ){
        this.j =j ;
    }

    private void produce(){
        j.handle(1);
        
        try{
            Thread.sleep((int)Math.random()*500);

        }catch(InterruptedException e){

        }
    }
    @Override
    public void run(){
        while (true ){
            produce();
        }
    }
}

class Consumer implements Runnable{
    private Jar j ;
    public Consumer (Jar j ){
        this.j = j;
    }
    private void consumer(){
        //do something
        j.handle(2);
        try{
            Thread.sleep((int)Math.random()*500);
        }catch(InterruptedException e){}
    }
    @Override
    public void run(){
        while(true){
            consumer();
        }
    }


}

class Jar {
    private volatile boolean available;
    private volatile BlockingQueue<Integer> queue1;
    private volatile BlockingQueue<Node1> queue2;
   private Scanner c ;
    Jar(Scanner c ){
        this.c = c;
        available = false ;
        queue1  = new LinkedBlockingDeque<Integer>();
        queue2 = new LinkedBlockingDeque<Node1>();
    }
    



    public void handle (int n){
        
        switch(n){
            case 1 :
                put();
            break ;
            case 2:
                get();     
            break;
            default:
                break;
        }

    }
    
    
    private synchronized void put(){
        System.out.println("Enter Number/ Command :  ");
        String val = c.next();
        synchronized(this){
            while(available ){

                try{
                    wait();
                    
                }
                catch (InterruptedException e){}
            }
            
            try {
                queue1.add(Integer.parseInt(val));

            }catch(NumberFormatException e){
                if (val.equals("display")){
                    while(!queue2.isEmpty()){
                        Node1 x = queue2.remove();
                        System.out.println(x.getTerm()+ " fibonnaci number is "+  x.getData()+ ". Computation Time :(nano Time) "+ x.getTime());
                    }
                }
                else if (val.equals("exit")){
                    System.exit(0);
                }
                else {
                    System.out.println("What was that ?");
                }
            }
            if (!queue1.isEmpty()){
                available = true;
                notifyAll();
            }
        }
    }


    private void get(){
        int number = -1 ; 
        synchronized(this){
            while(!available || queue1.isEmpty()){
                try{ wait();
                }
                catch (InterruptedException e){}}
            available = false ;
            number = queue1.remove();
            
        
        long t = System.nanoTime();
        int data = Flyweight.compute(number);
        long n = System.nanoTime();
        queue2.add(new Node1(data, number, n-t));
        notifyAll();
    }

        

}


}


class Flyweight{
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    
    public static int compute(int x ){
        
        if (map.containsKey(x)) return map.get(x);
        else if (x<2){
            return x;
        }
        else {
            map.put(x,compute(x-1)+compute(x-2));
            return map.get(x);
        }
    } 
}