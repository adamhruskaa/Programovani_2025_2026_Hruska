package Halda_pokracovani_16_4;

public class HaldaUpgrade {
        int[] halda;
        int zaplnenost;
    
        public HaldaUpgrade(int velikost) {
            halda = new int[velikost];
            zaplnenost = 0;
        }
    
        public void pridej(int hodnota) {
            if (zaplnenost >= halda.length) {
                System.out.println("Hald je plná, nelze přidat další prvek.");
                return;
            }
            halda[zaplnenost] = hodnota;
            zaplnenost++;
            upravHaldu();
        }
    
        private void upravHaldu() {
            int index = zaplnenost - 1;
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (halda[index] > halda[parent]) {
                    int temp = halda[index];
                    halda[index] = halda[parent];
                    halda[parent] = temp;
                    index = parent;
                } else {
                    break;
                }
            }
        }
    
        public void vypisHaldu() {
            for (int i = 0; i < zaplnenost; i++) {
                System.out.println(halda[i]);
            }
        }
    
        public static void main(String[] args) {
            HaldaUpgrade haldaUpgrade = new HaldaUpgrade(10);
            haldaUpgrade.pridej(5);
            haldaUpgrade.pridej(10);
            haldaUpgrade.pridej(3);
            haldaUpgrade.pridej(8);
            System.out.println("Obsah haldy:");
            haldaUpgrade.vypisHaldu();
        }
}
