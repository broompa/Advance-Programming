/*
Lavanya Verma
2018155
Advance Programming assignment 2
18/8/2019
IIITD
UG-2nd Year

*/
import java.util.Scanner;
import java.util.ArrayList;
class Mercury{
    public static ArrayList<Merchant> Mrc;
    public static ArrayList<Customer> Cst;
    public static ArrayList<Item> itm;
    static int s,t;// variable for required interface options and input handling
    static Person sel;// selected customer or merchant
    static Account mainAct;// company account
    public static ArrayList<String> catList;//category list 
    public static void main(String[] args) {
        ///////////////////////////////////////////////////////////////////
        mainAct = new Account(0);
        s = 0;
        t=2;
        catList = new ArrayList<String>();
        Mrc = new ArrayList<Merchant>();
        Cst = new ArrayList<Customer>();
        itm =  new ArrayList<Item>();
        mainAct = new Account(0);
        Customer cus = new Customer("Ali", "Qutub Minar", new Account(100));
        Merchant mer = new Merchant("Jack","New boys Hostel IIITD", new Account (0));
        Mrc.add(mer);
        Cst.add(cus);
        cus = new Customer("nobby", "Under the rainbow, Dreamland ", new Account(100));
        mer = new Merchant("John","Civil Lines ",new Account(0));
        Mrc.add(mer);
        Cst.add(cus);
        cus = new Customer("Bruno", "Monestry, Shimla", new Account(100));
        mer = new Merchant("James", "Babarpur", new Account(0));
        Mrc.add(mer);
        Cst.add(cus);
        cus = new Customer("Borat", "Winden", new Account(100)); //  fun hint for dark fans (Netflix) 
        mer = new Merchant("Jeff","Agra",new Account(0));
        Mrc.add(mer);
        Cst.add(cus);
        cus = new Customer("Aladeen", "Flying Carpet", new Account(100));
        mer = new Merchant("Joseph", "London", new Account(0));
        Mrc.add(mer);
        Cst.add(cus);
        Scanner c = new Scanner(System.in);
        ///////////////////////////////////////////////////////////////////
        while(true){
            if (s==0){
                dispMenu();
                int val = c.nextInt();           
                switch(val){
                    case 1:
                    dispM();
                    int mChoice = c.nextInt();
                    sel = Mrc.get(mChoice-1);
                    s=1;
                    t=0;

                    break;
                    case 2:
                    dispC();
                    int cChoice = c.nextInt();
                    sel = Cst.get(cChoice -1 );
                    s=1;
                    t=1;

                    break;
                    case 3:
                    dispM();
                    dispC();
                    String n = c.next(); // M or C
                    int v = c.nextInt();
                    if (n.equals("M")){
                        sel = Mrc.get(v-1);
                    }
                    else if (n.equals("C")){
                        sel = Cst.get(v-1);

                    }
                    sel.dispPerson();

                    break;
                    case 4:
                    System.out.println("Company's account balance is "+ mainAct.getMBalance());
                    break;
                    case 5 :
                    System.exit(0);

                    break;
                    
                }

            }
            else if (s==1){
                sel.dispMenu();
                if (t==0){// merchant menu handling 
                    int v = c.nextInt();
                    Merchant selM = (Merchant) sel;
                    switch(v){
                        case 1:
                            if (selM.limitReached()){
                                System.out.println("You cannot add more items.");
                                break;
                            }
                            System.out.println("Enter item details");
                            System.out.println("item name:");
                            String name = c.next();
                            System.out.println("item price");
                            double price = c.nextDouble() ;
                            System.out.println("item quantity");
                            int q = c.nextInt();
                            System.out.println("item category");
                            String cat = c.next();
                            selM.additem();
                            Item itx = new Item(name,q,sel.getName(),cat,price);
                            itm.add(itx);
                            if (!catList.contains(cat)){
                                catList.add(cat);
                            }
                            System.out.print(itm.size()+" ");
                            itx.disp();
                            break;
                        case 2:{
                            ArrayList<Integer> valids = dispMyItems(sel.getName());
                            int vc = c.nextInt();// valid choice of user(merchant ) , item belongs to him
                            if (valids.contains(vc)){
                                System.out.println("Enter edit details");
                                System.out.println("item price:"); 
                                double newPrice = c.nextDouble();
                                System.out.println("item quantity:");
                                int newQuantity = c.nextInt();
                                itm.get(vc-1).mutate(newPrice,newQuantity);
                                System.out.print(vc+" ");
                                itm.get(vc-1).disp();
                                
                                
                            }
                            else {
                                System.out.println("Not your item. Please make valid choice next time.");
                            }
                        }
                        break;
                        case 3 :{
                            listCat();
                            int catChoice= c.nextInt();
                            dispItem(catList.get(catChoice-1));
                            
                        }
                        break;
                        case 4:{
                            ArrayList<Integer> valids  = dispMyItems(sel.getName());
                            int vc = c.nextInt();// valid choice of user(merchant ) , item belongs to him
                            if (valids.contains(vc)){
                                System.out.println("Choose offer");
                                System.out.println("1) buy one get one");
                                System.out.println("2) 25% off");
                                int off = c.nextInt();
                                if (off==1){
                                    itm.get(vc-1).setOffer("buy one get one");
                                }
                                else if (off==2){
                                    itm.get(vc-1).setOffer("25% off");
                                }
                                System.out.print(vc+" ");
                                itm.get(vc-1).disp();

                                
                                
                            }
                            else {
                                System.out.println("Not your item. Please make valid choice next time.");
                            }


                        } 
                        break;
                        case 5:
                            System.out.println(selM.rewards());
                        break;
                        case 6:
                            s= 0;
                            t=2;
                        break;
                    }
                }
                else if (t==1){
                    Customer selC = (Customer) sel;
                    int v = c.nextInt();
                    switch(v){// customer input handling
                        case 1:{
                            listCat();
                            int cChoice = c.nextInt();
                            dispItem(catList.get(cChoice-1));
                            System.out.println("Enter item code");
                            int code = c.nextInt();
                            System.out.println("Enter item qauntity");
                            int q = c.nextInt();
                            System.out.println("Choose method of transaction");
                            System.out.println("1) Buy item");
                            System.out.println("2) Add item to cart");
                            System.out.println("Exit");
                            int way = c.nextInt(); // payment method
                            if (way == 3){ break; }
                            else if (way == 2){
                                selC.addToCart(new cItems(q,itm.get(code-1)));
                            }
                            else if (way == 1){
                                buyNow(itm.get(code-1), selC, getMerchantbyName(itm.get(code-1).getMName()), q);

                            }        

                            break;
                        }
                        case 2:{
                            double p = 0;
                            ArrayList<cItems> cart = selC.getCart();
                            for (int x = 0 ; x<cart.size();x++){
                                if (cart.get(x).getCount()>cart.get(x).getItem().getQuantity()){
                                    System.out.println("Out of stock");
                                    cart.clear();
                                    break;
                                }
                                else {
                                    if(cart.get(x).getItem().getOffer().equals("None")){
                                        p+=cart.get(x).getItem().getPrice()*cart.get(x).getCount()*1.005;
                                    }
                                    else if (cart.get(x).getItem().getOffer().equals("25% off")){
                                        p+=cart.get(x).getItem().getPrice()*cart.get(x).getCount()*0.75*1.005;      

                                    }
                                    else if (cart.get(x).getItem().getOffer().equals("buy one get one")){
                                        int qEff = 0 ;
                                        if ( cart.get(x).getCount()%2==0){qEff=cart.get(x).getCount()/2;}
                                        else{
                                            qEff =1+cart.get(x).getCount()/2;
                                        }
                                        p+=cart.get(x).getItem().getPrice()*qEff*1.005;        
                                    }
                                }
                            }
                            if (p>selC.getAcc().getMBalance() + selC.getAcc().getRBalance()){
                                System.out.println("Out of Money");
                                cart.clear();
                                break;
                            }
                            for (int x = 0 ; x<cart.size();x++){
                                buyNow(cart.get(x).getItem(), selC, getMerchantbyName(cart.get(x).getItem().getMName()), cart.get(x).getCount());
                            }
                            




                            break;
                        }
                        case 3 :
                            selC.rewards();
                        break;
                        case 4:
                            selC.lastorders();
                        break;
                        case 5:
                        s= 0;
                        t=2;
                        break;
                    }

                }
            }
            
        
        }

        
    }


    static void buyNow(Item i , Customer cus,Merchant mer, int dQ ){// dQ is desired qauntity
        if (i.getOffer().equals("None")){
            if (cus.getAcc().getMBalance()+cus.getAcc().getRBalance()< (double)i.getPrice()*(1+0.005)*dQ ){
                System.out.println("Not enough Money");
            }
            else if (dQ> i.getQuantity() ){
                System.out.println("Desired Quantity not availble.");
            }
            else {
                i.setQuantity(i.getQuantity()-dQ);
                System.out.println("Bought item "+ i.getName() + " quantity: " +dQ +" for Rs. " +i.getPrice()*dQ+" from Merchant " + i.getMName());
                cus.addOrder("Bought item "+ i.getName() + " quantity: " + dQ+" for Rs. " +i.getPrice()*dQ+" from Merchant " + i.getMName() );
                mer.Contribute(i.getPrice()*dQ*0.005);
                cus.getAcc().decrement(i.getPrice()*dQ, mer.getAccount(), mainAct);
            }
            
        }
        else if (i.getOffer().equals("25% off")){
            double pr = i.getPrice()*0.75;
            if (cus.getAcc().getMBalance()+cus.getAcc().getRBalance()< (double)pr*(1+0.005)*dQ ){
                System.out.println("Not enough Money");
            }
            else if (dQ> i.getQuantity() ){
                System.out.println("Desired Quantity not availble.");
            }
            else {
                i.setQuantity(i.getQuantity()-dQ);
                System.out.println("Bought item "+ i.getName() + " quantity: "+dQ + " for Rs. " +pr*dQ+" from Merchant " + i.getMName());
                cus.addOrder("Bought item "+ i.getName() + " quantity: " + dQ+" for Rs. " +pr*dQ+" from Merchant " + i.getMName() );
                mer.Contribute(pr*dQ*0.005);
                cus.getAcc().decrement(pr*dQ, mer.getAccount(), mainAct);
            }

        }
        else if (i.getOffer().equals("buy one get one")){
            int qEff ; // effective quantity to buy to fulfill desired quantity
            if (dQ%2==0){ qEff=dQ/2;}
            else {qEff = dQ/2+1;}
            if (cus.getAcc().getMBalance()+cus.getAcc().getRBalance()< (double)i.getPrice()*(1+0.005)*qEff ){
                System.out.println("Not enough Money");
            }
            else if (dQ> i.getQuantity() ){
                System.out.println("Desired Quantity not availble.");
            }
            else {
                i.setQuantity(i.getQuantity()-dQ);
                System.out.println("Bought item "+ i.getName() + " quantity: "+dQ + " for Rs. " +i.getPrice()*qEff+" from Merchant " + i.getMName() );
                cus.addOrder("Bought item "+ i.getName() + " quantity: " + dQ+" for Rs. " +i.getPrice()*qEff+" from Merchant " + i.getMName() );
                mer.Contribute(i.getPrice()*qEff*0.005);
                cus.getAcc().decrement(i.getPrice()*qEff, mer.getAccount(), mainAct);
            }

        }

    }

    static Merchant getMerchantbyName(String Name ){
        Merchant mx = Mrc.get(0);
        for (int x  = 0 ;x < Mrc.size();x++){
            if (Mrc.get(x).getName().equals(Name)){
                return Mrc.get(x);
            }
        }
        return mx;
            
    }



    static void dispMenu(){ // display mercury main menu
        
        System.out.println("Welcome to Mercury" );
        System.out.println("1) Enter as Merchant " );
        System.out.println("2) Enter as Customer " );
        System.out.println("3) See user details " );
        System.out.println("4) Company account balance " );
        System.out.println("5) Exit " );
    }

    static void listCat(){ // display all available categories
        System.out.println("Choose a category");
        for (int x = 0 ; x< catList.size();x++){
            System.out.println(x+1+") "+catList.get(x));
        }
    }   


    static void dispItem(String cat){ // dislplay items that belongs to a particular category
        for (int x  = 0 ; x<itm.size() ; x++){
            if (itm.get(x).getCat().equals(cat)){
                System.out.print(x+1+" ");
                itm.get(x).disp();
            }
        }   
    }

    static ArrayList<Integer> dispMyItems(String mName){ // to display item belong particular merchant 
        ArrayList<Integer> vChoice = new ArrayList<Integer>();
        System.out.println("choose item by code");
        for (int x = 0 ;x<itm.size();x++){
            if (itm.get(x).getMName().equals(mName)){
                vChoice.add(x+1);
                System.out.print(x+1+" ");
                itm.get(x).disp();
            }
        }
        return vChoice;
    }


    static void dispM(){// display all the merchants
        System.out.println("choose merchant ");
        for (int x = 0 ; x<Mrc.size();x++){
            System.out.println(x+1+" "+Mrc.get(x).getName());
        }
    }
    static void dispC(){ // display all the customers
        System.out.println("choose customer");
        for (int x =0 ; x<Cst.size();x++){
            System.out.println(x+1+" "+Cst.get(x).getName());
        }
    }

}
interface Person{
    void dispMenu();
    void dispPerson();
    String getName();
}
class Merchant implements Person{
    private String name,address ;
    private int limit,reward;// limit of slots , account balance , rewards slots count , contribution in mercury
    private int aSlots;// active slots count 
    private Account acc;
    private double Contribution;
    public Merchant(String name ,String address, Account myacc){
        this.name = name ;
        this.limit = 10;
        this.address = address;
        this.acc= myacc;
    }


    @Override
    public String getName(){ return this.name;}

    public boolean limitReached(){
        return !(limit > aSlots);
    }


    public void Contribute(double c){
        this.Contribution += c;
    }

    public void additem(){
        this.aSlots+=1;
    }
    public int rewards(){return this.limit - 10 ;}

    @Override
    public void dispMenu(){
        System.out.println("Welcome " + this.name);
        System.out.println("Merchant Menu");
        System.out.println("1) Add item ");
        System.out.println("2) Edit item ");
        System.out.println("3) Search by category ");
        System.out.println("4) Add offer ");
        System.out.println("5) Rewards won");
        System.out.println("6) Exit ");
           
    }

    @Override 
    public void dispPerson(){
        System.out.println(this.name+" " +this.address+ " "+this.Contribution);

    }

    public Account getAccount(){return this.acc;}


}
class Customer implements Person{
    private String name,address ;
    private int oCount; // orders count, placed orders
    private ArrayList<cItems> cart ;
    private Account acc;//account 
    private ArrayList<String> record;
    
    public Customer(String name, String address , Account myacc  ){
        this.name = name;
        this.address = address;
        this.acc = myacc;
        record= new ArrayList<String>();
        cart = new ArrayList<cItems>();
    }


    public void rewards(){
        System.out.println("Rewards won: " + this.acc.getRBalance() );
    }

    public void lastorders(){
        int cnt = 0 ;
        for (int x = 0 ; x<record.size();x++){
            System.out.println(record.get(x));
            if (++cnt>9){
                break;
            }
        }

    }

    public ArrayList<cItems> getCart(){ return this.cart;}

    @Override
    public String getName(){ return this.name;}

    @Override
    public void dispMenu(){
        System.out.println("Welcome " + this.name);
        System.out.println("Customer Menu");
        System.out.println("1) Search item");
        System.out.println("2) checkout cart");
        System.out.println("3) Reward won ");
        System.out.println("4) print latest orders ");
        System.out.println("5) Exit");
    }

    @Override 
    public void dispPerson(){
        System.out.println(this.name + " "+this.address+" " +this.oCount);
    }

    
    public void addOrder(String x){
        record.add(0,x);
        oCount+=1;
        if (oCount> (((int)this.acc.getRBalance()/10)+1)*5){
            this.acc.setRBalance(this.acc.getRBalance()+10);
        }
    }

    public void addToCart(cItems c){
        cart.add(c);
    }
    public Account getAcc() { return this.acc;}
    


}
class Item {
    private int  quantity; 
    private String name, category, mName;
    private String offer;// 0 -  No offer  , 1 - 25 % off , 2 - buy one get one 
    private double price;
    public Item (String name , int quantity,String mName, String category,double price ){
        this.price = price ;
        this.name = name ;
        this.quantity = quantity;
        this.mName = mName ;
        this.category = category;
        this.offer ="None";
    }
    public void disp(){
        System.out.println(this.name +" "+this.price+" "+this.quantity+" "+this.offer +" " + this.category );
    }
    public String getMName(){ return this.mName;}
    public void mutate(double price, int quantity){// function for editing price and quantity 
        this.price = price;
        this.quantity = quantity;
    }
    public String getCat() { return this.category;}

    public void setOffer(String offer){
        this.offer = offer;
    }
    public String getOffer(){return this.offer;}
    public double getPrice(){ return this.price;}
    public int getQuantity(){ return this.quantity;}
    public void setQuantity( int q){
        this.quantity=q;
    }
    public String getName() {return this.name;}

} 


class cItems{//class for cart items
    private int count ;
    private Item itm;
    public cItems(int count,Item itm){
        this.count = count;
        this.itm = itm;
    } 
    public int getCount(){ return this.count;}
    public Item getItem(){ return this.itm;}
}

class Account {
    private double mBalance ;//main balance
    private double rBalance ;// reward balance
    public Account (double mBalance){
        this.mBalance = mBalance;
        this.rBalance = 0;
    }
    public double getMBalance(){
        return this.mBalance;
    }
    
    public double getRBalance(){
        return this.rBalance;
    }

    public void setMBalance(double mBalance ){
        this.mBalance = mBalance;

    }
    public void setRBalance(double rBalance){ this.rBalance = rBalance;}
    public void decrement(double x,Account mrc,Account cmpy ){// transaction value , merchant account , company account 
        double y = x+ x*0.005;// this function will only be called when it have sufficient amount to pay price as well as tax
        if (y>this.mBalance){
            y=y-this.getMBalance();
            this.setMBalance(0);
            setRBalance(getRBalance()-y);
        }else{
                this.setMBalance(this.getMBalance()-x*(1+0.005));
        }
        mrc.setMBalance(mrc.getMBalance() + x-x*0.005);
        cmpy.setMBalance(cmpy.getMBalance()+x*0.01);

    }
    public void reward(){
        this.rBalance +=10;
    }
}