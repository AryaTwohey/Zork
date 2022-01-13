package zork; 

    public class Weapon extends Item{ 
        private int weight;
        private String name;
        private boolean isOpenable;
        private String description;
        private int weaponDamage; 

        public Weapon(int weight, String name, boolean isOpenable, String description, int weaponDamage) {
          super(weight, name, isOpenable, description); 
          this.weaponDamage = weaponDamage;
        }

        
        public Weapon() {
        }

        public void setDamage(int weaponDamage) {
            this.weaponDamage = weaponDamage;
        }
     
        public int getDamage() {
          return weaponDamage;
      }
        
      }
 

