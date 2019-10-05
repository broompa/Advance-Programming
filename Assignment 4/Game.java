import java.util.*;
/*
Lavanya Verma
2018155
ECE 
Completition date : 1/08/2019
Advance programming Assignment 4
IIIT-D

*/



class Game {
    public static Scanner c ;
    public static ArrayList<User> users; 
    public static User uDummy; 
    public static Random random;
    public static Location [] graph ;
    public static int cLocation;
    public static User cUser;// current active user
    public static int lLocation;

    public static void main(String[] args) {
        cLocation = 30;// starting location
        lLocation = 31;
        
        uDummy = new User("Dummy",0);
        graph = new Location[30];

        random = new Random();
        c = new Scanner(System.in);
        int mState =0;
        users = new ArrayList<User>();

        for (int x =0 ; x<graph.length;x++){
            graph[x] = new Location(random.nextInt(2)+1);
        }

        graph[9 + random.nextInt(20)].setMonster(4);

        

////////////////////////////////////////////////////


        while (true){        
            if (mState == 0){
                System.out.println("Welcome to ArchLegends"); 
                System.out.println("Choose your option");
                System.out.println("1) New User");
                System.out.println("2) Existing User");
                System.out.println("3) Exit");
                int val = c.nextInt();
                switch (val){
                    case 1:{
                        users.add(createUser());                 
                        break;

                    }
                    case 2:{
                        
                        User u1 = getUser(); 

                        if (u1.equals(uDummy)){
                            System.out.println("No such user exists");
                        }else{
                            System.out.println("User Found... logging in");
                            cUser = u1;
                            startGame();
                        }
                        break;
                    }
                    case 3:{
                        System.exit(0);
                        break;
                    }
                }
            }

        }
    }


    public static void startGame(){
        while(true){
            printGraph(cLocation);
            int n = c.nextInt();
            if (cLocation == 30){
                switch(n){
                    case 1:{
                        System.out.println("Moving to location 0");
                        lLocation = cLocation;
                        cLocation = 0;
                        
                        break;
                    }
                    case 2:{
                        System.out.println("Moving to location 1");
                        lLocation = cLocation;
                        cLocation =1 ;
                        
                        break;
                    }
                    case 3:{
                        System.out.println("Moving to location 2");
                        lLocation = cLocation;
                        cLocation = 2;
                        
                        break;
                    }
                    case 4:{

                        return ;
                        //break;
                    }
                    default:{
                        cLocation= 0;
                        lLocation=0;
                    }
                }
            }
            else if (0<=cLocation && cLocation<27  ){
                switch(n){
                    case 1:{
                        System.out.println("Moving to location "+ (int)(cLocation+1));
                        lLocation= cLocation;
                        cLocation= cLocation+1;
                        
                        break;
                    }
                    case 2:{
                        System.out.println("Moving to location "+ (int)(cLocation+2));
                        lLocation= cLocation;
                        cLocation = cLocation+2;
                        break;
                    }
                    case 3:{
                        System.out.println("Moving to location "+ (int)(cLocation+3));
                        lLocation= cLocation;
                        cLocation = cLocation+3;
                        break;
                    }
                    case 4:{
                        int temp = cLocation;
                        cLocation= lLocation;
                        System.out.println("Moving to location "+ cLocation);
                        lLocation = temp;
                        break;
                    }
                }
            

            }
            else if (27<=cLocation && cLocation<30 ){
                switch(n){
                    case 1:{
                        System.out.println("Moving to location "+ 10);
                        lLocation = cLocation;
                        cLocation = 10;
                        break;
                    }
                    case 2:{
                        System.out.println("Moving to location "+ 20);
                        lLocation = cLocation;
                        cLocation = 20;
                        break;
                    }
                    case 3:{
                        System.out.println("Moving to location "+ 25);
                        lLocation = cLocation;
                        cLocation = 25;
                        break;
                    }
                    case 4:{
                        System.out.println("Moving to location "+ lLocation);
                        int temp = cLocation;
                        cLocation= lLocation;
                        lLocation = temp;
                        break;
                        
                    }
                }
            }
            if (cLocation<=29){
                int val = fightScene(graph[cLocation].getMonster(), cUser.getHero());
                if (val == 1){
                    System.out.println("Fight won proceed to the next location.");
                    System.out.println("If you would you like to buy a sidekick, type yes. Else type no to upgrade level.");
                    String response = c.next(); // yes / no
                    switch(response){
                        case "yes":{
                            System.out.println("Your current XP is "+cUser.getHero().getXp());
                            System.out.println("If you want to buy a minion, press 1.");
                            System.out.println("If you want to buy a knight, press 2.");
                            int choice = c.nextInt(); // sidekick choice
                            System.out.print("XP to spend: ");
                            int spendXp = c.nextInt();///// function to add sidekick
                            cUser.getHero().addSidekick(choice, spendXp);
                            break;

                        }
                        case "no":{
                            break;
                        }
                    }

                }else if (val == 2){
                    System.out.println("Fight lose going back to starting location ");
                    lLocation = cLocation;
                    cLocation = 30;
                }
            }else{
                continue;
            }
            


    }
    }
    public static void printGraph(int n ){
        if (n==30){
            int h = 0;
            System.out.println("You are at starting location.Choose Path:");
            System.out.println("1) Go to location 0");
            System.out.println("2) Go to location 1");
            System.out.println("3) Go to location 2");
            System.out.println("4) Enter to exit");
            //////////////////hint
            if(graph[0].getMonster().getLevel()<graph[1].getMonster().getLevel()){
                h = 0;
            }else {
                h =1;
            }
            if (graph[h].getMonster().getLevel()<graph[2].getMonster().getLevel()){
                

            }else {
                h = 2;
            }
            System.out.println("Hint : Choose "+h + " Location");
         }
        else if (0<=n&& n<27){
            int h =0;
            System.out.println("You are at "+n+" location.Choose Path:");
            System.out.println("1) Go to location "+(int)(n+1));
            System.out.println("2) Go to location "+ (int)(n+2));
            System.out.println("3) Go to location "+ (int)(n+3));
            System.out.println("4) Go back");
            //////////////hint
            if(graph[n+1].getMonster().getLevel()<graph[n+2].getMonster().getLevel()){
                h = n+1;
            }else {
                h =n+2;
            }
            if (graph[h].getMonster().getLevel()<graph[n+3].getMonster().getLevel()){
                

            }else {
                h = n+3;
            }
            System.out.println("Hint : Choose "+h + " Location");

        }
        else if (n==28 || n==29){
            int h =n;
            System.out.println("You are at "+n+" location.Choose Path:");
            System.out.println("1) Go to location "+(int)(10));
            System.out.println("2) Go to location "+(int)(20));
            System.out.println("3) Go to location "+ (int)(25));
            System.out.println("4) Go back");
            //////////hint
            if(graph[10].getMonster().getLevel()<graph[20].getMonster().getLevel()){
                h =10;
            }else {
                h =20;
            }
            if (graph[h].getMonster().getLevel()<graph[25].getMonster().getLevel()){
                

            }else {
                h = 25;
            }
            System.out.println("Hint : Choose "+h + " Location");

        }

    }



    public static int fightScene(Monster m , Hero h){
        int turn = 0; 
        boolean sp = false;// true indicates special power activated
        int sCount = 0; // count for super power on 4th move or after it
        int eff_defense =0;
        int lastMove = 0;
        int dcount=0;// count for 3 time super power will work;

        Sidekick s =  null;
        boolean clonning = false;
        Sidekick [] clones = new Sidekick[3];
        System.out.println("Fight Started. You are fighting a level "+ m.getLevel()+" Monster.");
        if (h.hasSidekick()){
            System.out.println("Type yes if you wish to use a sidekick, else type no.");
            String n = c.next();
            switch(n){
                case "yes":{
                    s = h.getStrongestSidekick();
                    break;
                }
                case "no":{}
            }
            if (s.getType().equals("Minion") && !s.getPUsed()){
                System.out.println("Press c to use cloning ability. Else press f to move to the fight");
                String clone = c.next();
                if (clone.equals("c")){
                    clonning =true;
                    s.UsedPower();
                    clones[0]= s.shallowClone();
                    clones[1]= s.shallowClone();
                    clones[2]= s.shallowClone();
                    System.out.println("clonning done.");                
                }
            }


        }

        while(m.getHp()>0 && h.getHp()>0){
            turn = turn^1;
            switch(turn){
                case 0:{

                    if (sp){
                        switch(h.getType()){
                            case "Warrior":{
                                if (dcount==0){
                                System.out.println("Attack and defense increased by 5 units");}
                                eff_defense = 5;

                                break;
                            }
                            case "Thief":{
                                if (dcount ==0){
                                double steal = m.getHp()*0.3;
                                h.setHp((int)(h.getHp()+steal));
                                m.setHp((int)(m.getHp()-steal));
                                System.out.println("You have stolen "+ (int) steal +" Hp from the monster!");                             
                                }
                                break;
                            }
                            case "Mage":{
                                double hh =  m.getHp()*0.05;
                                int dam = (int) hh;
                                m.setHp((int)(m.getHp()*0.95));
                                System.out.println("Spell inflicted "+dam +" damage to Monster!");

                                break;
                            } 
                            case "Healer":{
                                double hh =  h.getHp()*0.05;
                                int heal = (int) hh;
                                h.setHp(h.getHp()+heal);
                                System.out.println("Hero healed by "+ heal +" Hp!");

                                break;
                            }
                        }
                    }
                    if (s!=null && s.getType().equals("Knight") && m.getLevel()==2){
                        eff_defense+=5;
                        System.out.println("Knight reduced zombie attack by 5 hp");
                    }
                    if (lastMove== 2){// last move was defense
                        
                        eff_defense += h.armory();
                        System.out.println("Monster attacked reduced by "+ eff_defense+" .");
                    }
                    if(lastMove != 2 && eff_defense >0){
                        System.out.println("Monster attacked reduced by "+ eff_defense+" .");
                    }
                    System.out.println("Monster Attack!");
                    if (m.isLionfang() && random.nextInt(10)==9){
                        int val =eff_defense - h.getHp()/2;
                        if (val>0){
                            break;
                        }
                        System.out.println("Monster attacked and inflicted "+val+" damage to you.");
                        h.setHp(h.getHp()-(h.getHp()/2)+eff_defense);

                    }
                    else{
                        int val = (int)(getGaussian(0, m.getHp()/4));
                        if (val -eff_defense<= 0){
                            break;
                        }
                        System.out.println("Monster attacked and inflicted "+val+" damage to you.");
                        h.setHp(h.getHp()-val+eff_defense);
                        if (s!=null){
                            int val1 = (int)(val*1.5);
                            s.setHp(s.getHp()-val1);
                            if (clonning){
                                clones[0].setHp(clones[0].getHp()-val1);
                                clones[1].setHp(clones[1].getHp()-val1);
                                clones[2].setHp(clones[2].getHp()-val1);
                            }
                        }

                    }
                    eff_defense = 0;
                    break;

                }
                case 1:{
                    if (dcount>=3){
                        sp=false;
                        dcount = 0;
                    }
                    if (sp){
                        dcount++;
                    }
                    eff_defense=0;
                    sCount+=1;
                    System.out.println("Choose move:");
                    System.out.println("1) Attack");
                    System.out.println("2) Defense");
                    if (sCount>3){
                        System.out.println("3) Special Attack");

                    }

                    int choice = c.nextInt();
                    switch(choice){
                        case 1:{
                            System.out.println("You choose to attack ");
                            int att = h.attack(); // attack damage value
                            if (sp && h.getType().equals("Warrior")){
                                att +=5;
                            }
                            m.setHp(m.getHp()-att);
                            System.out.println("You attacked and inflicted "+att+" damage to the monster.");
                            lastMove =1;
                            if (s!=null &&s.getHp()>0){
                                if (clonning){
                                    m.setHp(m.getHp()-clones[0].attack());
                                    System.out.println("Sidekick attacked and inflicted "+clones[0].attack()+" damage to the monster.");
                                    System.out.println("Sidekick attacked and inflicted "+clones[1].attack()+" damage to the monster.");
                                    System.out.println("Sidekick attacked and inflicted "+clones[2].attack()+" damage to the monster.");
                                    m.setHp(m.getHp()-clones[1].attack());
                                    m.setHp(m.getHp()-clones[2].attack());
                                }
                                System.out.println("Sidekick attacked and inflicted "+s.attack()+" damage to the monster.");
                                m.setHp(m.getHp()-s.attack());
                            }

                            break;
                        }
                        case 2:{
                            lastMove =2;
                            System.out.println("You choose to defend");
                            break;
                        }
                        case 3:{

                            if (sCount<=3){
                                System.out.println("You cannot use this move ");
                                break;

                            }
                            System.out.println("Special Power activated");
                            lastMove = 3; 
                            sp = true;
                            sCount =0 ;                            
                            break;
                        }
                        
                    }


                }

            }
            int h_hp = (h.getHp()>0)?h.getHp():0;
            int m_hp = (m.getHp()>0)?m.getHp():0;
            System.out.println("Your Hp: " +h_hp+"/"+h.getHpLimit()+ " Monsters Hp: "+m_hp+"/"+ m.getHpLimit());
            if (s!=null && s.getHp()>0){
                int s_hp =(s.getHp()>0)?s.getHp():0;
                System.out.println("Sidekick's Hp: "+ s_hp+"/100");
                if(clonning){
                    int c0_hp = (clones[0].getHp()>0)?clones[0].getHp():0;
                    int c1_hp = (clones[1].getHp()>0)?clones[1].getHp():0;
                    int c2_hp = (clones[2].getHp()>0)?clones[2].getHp():0;
                    System.out.println("Sidekick's Hp: "+ c0_hp+"/100");
                    System.out.println("Sidekick's Hp: "+ c1_hp+"/100");
                    System.out.println("Sidekick's Hp: "+ c2_hp+"/100");
                }
            }

        }
        if (m.getHp()<=0){
            h.addXp(20*m.getLevel());
            System.out.println("Monster killed!");
            System.out.println(m.getLevel()*20+ "XP awarded");
            System.out.println("Hero Level:" + h.getLevel());
            h.recreate();
            m.recreate();
            return 1 ; // hero won
        }else if (h.getHp()<=0){
            System.out.println("Monster won");
            h.recreate();
            m.recreate();
            return 2;//monster won
        }
        else{
            System.out.println("Error during fight");
            return 0;
        }
        


    }


    public static double getGaussian(int l , int H){
        if (H-l<5){
            return (H-l)/2;
        }else{
            while(true){
                double d = random.nextGaussian()*(H-l)/200+(H-l)/2;
                if ( l<=d&& d<H ){
                    return d;
                }
            }
        }

    }
    




    public static User getUser(){
        System.out.println("Enter Username");
        String u = c.next();
        User n = uDummy;
        int i = 0;// denotes whether that username exists or not;
        for (int x =0 ; x<users.size();x++){
            if (users.get(x).getUsername().equals(u)){
                n = users.get(x);
                i = 1;
            }
        }
        if (i==0){
            System.out.println("No such username exists.");
        }

        return n;



    }

    public static User createUser(){
        System.out.println("Enter Username");
        String n = c.next();
        System.out.println("Choose your option");
        System.out.println("1) Warrior");
        System.out.println("2) Thief");
        System.out.println("3) Mage");
        System.out.println("4) Healer");
        int op = c.nextInt();
        User u = new User(n,op);
        System.out.println("User Creation done.Username: "+ u.getUsername() + ".Hero type: "+ u.getHeroType()+".Log in to play the  game .Exiting");
        return u;
    }




}
abstract class Character {
    protected int damage ;
    protected int hp;
    protected int level ;
    protected int hpLimit;
    public void setHp(int n){ 
        if (n > this.hpLimit){
            return;
        }
        
        this.hp = n;} 
    public int getHp(){ return this.hp;}
    public int getLevel(){ return this.level;}
    public int getHpLimit(){return this.hpLimit;}
}

class Monster extends Character {
    private final String type;
    public Monster(int n){
        String k ="";
        switch(n){
            case 1:
                this.hp = this.hpLimit =100;
                k = "Goblin";
                break;
            case 2:
                this.hp = this.hpLimit= 100;
                k = "Zombies";
                break;
            case 3:
                this.hp = this.hpLimit= 200;
                k = "Fiends";
                break;
            case 4:
                this.hp = this.hpLimit = 250;
                k = "Lionfang";
                break;
        }
        type = k;
        this.level = n;



    }
    public boolean isLionfang(){
        return type.equals("Lionfang");
    }
    public void recreate(){
        this.hp = this.hpLimit;
    }

}


class Hero extends Character{
    protected final String type ;
    protected final int armor ;
    protected int xp;
    private ArrayList<Sidekick> sidekicks;
    public Hero(String t){
        this.type = t ;
        this.hpLimit = 100;
        this.level = 1;
        this.sidekicks = new ArrayList<Sidekick>();
        this.hp = hpLimit;
        if (t.equals("Warrior")){
            this.damage = 10;
            this.armor = 3;
        }else if (t.equals("Thief")){
            this.damage = 6;
            this.armor = 4;
            
        }else if (t.equals("Mage")){
            this.damage = 5;
            this.armor  = 5;
        }else if (t.equals("Healer")){
            this.damage = 4;
            this.armor = 8;
        }else {
            this.armor = 0;
        }
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    public int getXp(){ return this.xp;}
    public int attack(){ return this.damage;} // return damage value
    public int armory(){ return this.armor;} // return defense / dodge value
    public void addXp(int n ){
        this.xp += n;
        if (this.xp>=20){
            this.level = 2;
        }
        if (this.xp >=60 ){
            this.level = 3;
        }
        if (this.xp>120){
            this.level = 4;
        }
        this.hpLimit = 100+ (this.level-1)*50;
        this.hp = this.hpLimit;
        
    }
    public void recreate(){
        this.hp = this.hpLimit;
        for (int x = 0 ; x<sidekicks.size();x++){
            if (sidekicks.get(x).getHp()>0){
            sidekicks.get(x).recreate();
            }  
            else {
                sidekicks.remove(x);
            }
        }
    }

    public void addSidekick(int n , int xp ){
        if (n == 1){
            if (xp<5){
                System.out.println("Need to invest more xp to get minion sidekick");
                return;
            }
            sidekicks.add(new Sidekick("Minion", xp));

        }else if (n==2){
            if (xp<8){
                System.out.println("Need to invest more xp to get knight sidekick");
                return ;
            }
            sidekicks.add(new Sidekick("Knight", xp));    
        }
        else {
            System.out.println("Something Wrong");
            return;
        }
        this.xp = this.xp - xp;
        this.addXp(0);
        System.out.println("You bought a sidekick:" + this.sidekicks.get(sidekicks.size()-1).getType());
        System.out.println("Xp of sidekick is "+ this.sidekicks.get(sidekicks.size()-1).getXp());
        System.out.println("Attack of sidekick is "+ this.sidekicks.get(sidekicks.size()-1).attack());        
        
    }
    public Sidekick getStrongestSidekick(){
        Collections.sort(sidekicks,new SidekickComparator());
        return sidekicks.get(sidekicks.size()-1);
    }
    public boolean hasSidekick(){
        return sidekicks.size()>0;

    }



}
class User {
    private final String username ;
    private final Hero hero ;

    public User(String u ,int n){
        username = u;
        Hero h = new Hero("Dummy");

        switch(n){
            case 1:{
                h = new Hero("Warrior");
                break;
            }
            case 2:{
                h = new Hero("Thief");
                break;
            }
            case 3:{
                h = new Hero("Mage");
                break;
            }
            case 4:{
                h = new Hero("Healer");
                break;
            }
        }
        hero = h;
    }
    public String getUsername(){ return this.username; }
    public String getHeroType(){
        return hero.getType();
    }
    public String Username(){
        return this.username;
    }
    public Hero getHero(){ return this.hero;}


}
class Location{
    private Monster mons;
    public Location(int n){
        mons  = new Monster(n);
    }
    public void setMonster(int n ){
        mons = new Monster(n);
    }
    public Monster getMonster(){
        return this.mons;
    }

}
class Sidekick extends Hero{
    private int prev_xp; // prev xp//5 - to increase damage with xp
    private boolean powerUsed;
    public Sidekick(String n , int xp){
        super(n);
        if (n.equals("Minion")){
            this.damage =1 ;
            this.damage += (int)((xp-5)*0.5);
        }else if (n.equals("Knight")){
            this.damage = 2;
            this.damage += (int)((xp-8)*0.5);
        }
        
        prev_xp= 0;
    }
    public void addXp(int xp){
        this.xp+=xp;
        if ((int)this.xp/5 > prev_xp ){
            this.damage +=  1;
            prev_xp = this.xp/5;
        }
        
    }
    public boolean getPUsed(){ return this.powerUsed;}
    public void UsedPower(){
        this.powerUsed = true;
    }
    public Sidekick shallowClone(){
        Sidekick c = new Sidekick(this.getType(), this.getXp());
        c.damage = this.damage;
        c.powerUsed = this.powerUsed;
        return c;

    }


}
class SidekickComparator implements Comparator<Sidekick>{
    public int compare(Sidekick a , Sidekick b){
        return a.getXp()-b.getXp();
    }
}