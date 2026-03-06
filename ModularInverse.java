public class ModularInverse {
    static long MOD= (long)(1e9 + 7);

    public static long power(long b, long e){  // Fast Exponentiation
        long ans=1;

        while(e > 0){
            if((e & 1) == 1){
                ans = (ans * b) % MOD;
            }

            b = (b * b) % MOD;
            e = e >> 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        MOD= (long)(1e9 + 7);
        int n=5;
        long k=3;

        long invPow[]=new long[n];
        
        // inverse of k (mod M) => k^-1 = k^(M-2) (mod M)  where M is Prime
        long invK= power(k, MOD - 2);  // k^-1

        invPow[0]=1;
        for(int i=1;i<n;i++){
            invPow[i] = (invPow[i-1] * invK) % MOD;  // ex :- k^-3 = k^-2 * k^-1
        }

        for(long x : invPow){
            System.out.print(x + " ");
        }
    }
}