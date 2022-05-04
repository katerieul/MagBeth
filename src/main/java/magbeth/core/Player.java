package magbeth.core;
//is that ok?
public class Player {
    int color;
    public boolean human;
    public Player() {}
    public Player(int color){
        this.color=color;
    }
    public boolean isHuman(){return this.human;}
    public class Human extends Player {
        public Human(int color) {
            super();
            this.color = color;
            this.human = true;
        }
    }
    public class Computer extends Player {
        public Computer(int color) {
            this.color = color;
            this.human = false;
        }
    }
}
