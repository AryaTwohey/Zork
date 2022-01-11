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
      
        public void open() {
          if (!isOpenable)
            System.out.println("The " + name + " cannot be opened.");
      
        }
      
       public String getDescription() {
          return description;
        }
        public void setDescription(String description) {
          this.description = description;
        }

        public void setDamage(String weaponDamage) {
            this.weaponDamage = weaponDamage;
        }
        
      
        public String getName() {
          return name;
        }
      
        public void setName(String name) {
          this.name = name;
        }
      

      
      
      
      
      }
 

