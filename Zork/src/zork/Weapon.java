//Lara and Muriel
package zork; 

    public class Weapon extends Item{ 
        private int weight;
        private String name;
        private boolean isOpenable;
        private String description;
        private int weaponDamage; 

        public Weapon(int weight, String name, boolean isOpenable, String description, int weaponDamage) {
          /**
           * constructors within the game
           * weight, name, if openable and description
           */
          super(weight, name, isOpenable, description); 
          this.weaponDamage = weaponDamage;
        }

        
        public Weapon() {
        }

        public void setDamage(int weaponDamage) {
          /**
           * the constructors for the weapon damage
           */
            this.weaponDamage = weaponDamage;
        }
     
        public int getDamage() {
          /** 
           * returns the weapon damage 
           */
          return weaponDamage;
      }
        
      }
 

