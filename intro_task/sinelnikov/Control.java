public class Control {
        private boolean flag;
        private int index;
        public Control(boolean flag,int index){
            this.flag = flag;
            this.index = index;
        }
        public boolean getFlag(){
            return this.flag;
        }
        public int getIndex(){
            return this.index;
        }
        public void changeFlag(){
            this.flag = false;
        }
        public void increaseIndex(){
            this.index++;
        }
}
