package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
       super(name,balance,5000);
       this.tradeLicenseId=tradeLicenseId;

       if(balance < 5000){
           throw new Exception("Insufficient Balance");
       }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!this.isValid(this.tradeLicenseId)){
            String rearrangeId=this.rearrangeString(this.tradeLicenseId);
            if(rearrangeId == ""){
                throw new Exception("Valid License can not be generated");
            }

        }
    }
    public String rearrangeString(String str){
        int n=str.length();
        int []cnt= new int[26];
        for(int i=0;i<26;i++){cnt[i]=0;
        }
        char[] ch=str.toCharArray();
        int count=ch.length;

        int val;
        for(int j=0;j<count;j++){
            val=ch[j];
            cnt[val-65]++;
        }
        int k;
        char letter=this.maxcount(cnt);
        count=cnt[letter-65];
        if(count > (n+1)/2){
            return "";
        }
        else{
            String res="";
            for(k=0;k<n;k++){
                res=res+"";
            }

            for(k=0;count> 0;count--){
                res=res.substring(0,k)+letter+res.substring(k+1);
                k+=2;
            }
            cnt[letter-65]=0;

            for(int i=0;i<26;i++){
                while(cnt[i] >0){
                    k=k>n?1:k;
                    String s=res.substring(0,k);
                    res=s+(char)(65+i)+res.substring(k+1);
                    k+=2;
                    int st=cnt[i]--;
                }
            }
            return res;
        }

    }
    public char maxcount(int[] c){
        int max=0;
        char d=0;
        for(int i=0;i<26;i++){
            if(c[i] > max){
                max=c[i];
                d=(char)(65+i);
            }
        }
        return d;
    }

    public boolean isValid(String Id){
        for(int i=0;i<Id.length()-1;i++){
            if(Id.charAt(i) != Id.charAt(i)){
                return true;
            }
        }
        return false;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
