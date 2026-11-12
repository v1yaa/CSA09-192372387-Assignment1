package parking.service;
public class ParkingFacility {
 private final String[][] slots;
 public ParkingFacility(int[] capacities){
  if(capacities==null||capacities.length==0)throw new IllegalArgumentException();
  slots=new String[capacities.length][];
  for(int i=0;i<capacities.length;i++){
   if(capacities[i]<0)throw new IllegalArgumentException("Negative capacity");
   slots[i]=new String[capacities[i]];
  }
 }
 public int[] allocate(String reg){
  if(reg==null||reg.isBlank())return null;
  for(int f=0;f<slots.length;f++)for(int s=0;s<slots[f].length;s++)
   if(slots[f][s]==null){slots[f][s]=reg;return new int[]{f,s};}
  return null;
 }
 public boolean release(int f,int s){
  if(f<0||f>=slots.length||s<0||s>=slots[f].length||slots[f][s]==null)return false;
  slots[f][s]=null;return true;
 }
}
