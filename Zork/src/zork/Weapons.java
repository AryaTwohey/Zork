package zork; 

    public class Weapons extends Item{ 
        private int weight;
        private String name;
        private boolean isOpenable;
        private String description;
        private String weaponDamage; 

        public Weapons(int weight, String name, boolean isOpenable, String description, String weaponDamage) {
          super(weight, name, isOpenable, description); 
          this.weaponDamage = weaponDamage;
        }

        
        public Weapons() {
        }

        public void setDamage(String weaponDamage) {
            this.weaponDamage = weaponDamage;
        }
     
        public String getDamage(String weaponDamage) {
          return weaponDamage;
      }
        
      }
 

