import java.io.*;
import java.util.*;

public class Main {

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    static long MOD = (long) (1e9 + 7);

    static long powerLL(long x, long n)
    {
        long result = 1;
        while (n > 0)
        {
            if (n % 2 == 1)
            {
                result = result * x % MOD;
            }
            n = n / 2;
            x = x * x % MOD;
        }
        return result;
    }

    static long powerStrings(String sa, String sb)
    {
        long a = 0, b = 0;

        for (int i = 0; i < sa.length(); i++)
        {
            a = (a * 10 + (sa.charAt(i) - '0')) %
                    MOD;
        }

        for (int i = 0; i < sb.length(); i++)
        {
            b = (b * 10 + (sb.charAt(i) - '0')) %
                    (MOD - 1);
        }

        return powerLL(a, b);
    }

    static long gcd(long a, long b)
    {
        if (a==0) return b;
        else return gcd(b%a,a);
    }

    static long lcm(long a, long b)
    {
        return (a*b)/gcd(a,b);
    }

    static int lower_bound(List<Integer> list, int k)
    {
        int s = 0;
        int e = list.size();

        while (s!=e)
        {
            int mid = (s+e)>>1;

            if (list.get(mid)<k) s = mid+1;
            else e = mid;
        }

        if (s == list.size()) return -1;
        return s;
    }

    static int upper_bound(List<Integer> list, int k)
    {
        int s = 0;
        int e = list.size();

        while (s!=e)
        {
            int mid = (s+e)>>1;

            if (list.get(mid)<=k) s = mid+1;
            else e = mid;
        }

        if (s == list.size()) return -1;
        return s;
    }

    static void addEdge(ArrayList<ArrayList<Integer>>graph, int edge1, int edge2)
    {
        graph.get(edge1).add(edge2);
        graph.get(edge2).add(edge1);
    }

    static class Pair<X,Y>
    {
        public final X first;
        public final Y second;

        Pair(X first, Y second)
        {
            this.first = first;
            this.second = second;
        }

        public static <X,Y> Pair <X,Y> of (X a, Y b)
        {
            return new Pair<>(a,b);
        }

        public String toString()
        {
            return "("+first+","+second+")";
        }
    }

    static class Comparee
    {
        static void compare(List<Pair<Integer, Integer>> pairs, int m)
        {
            Collections.sort(pairs, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    return p1.second - p2.second;
                }
            });
        }
    }

    public static void main(String[] args) throws java.lang.Exception
    {
        try
        {
            //FastReader fr = new FastReader();
            Reader fr = new Reader();

            try(OutputStream out = new BufferedOutputStream(System.out))
            {
                int n = fr.nextInt();
                int m = fr.nextInt();

                List<Pair<Integer,Integer>> pairs = new ArrayList<>();
                Pair<Integer, Integer> p1;

                for (int i=0;i<m;i++)
                {
                    p1 = Pair.of(fr.nextInt(), fr.nextInt());

                    pairs.add(p1);
                }

                //out.write((pairs+"").getBytes());
                Comparee.compare(pairs,m);

                //for (int i=0;i<m;i++) out.write((pairs.get(i).first+" "+pairs.get(i).second+"\n").getBytes());

                long sum = 0;
                int count = 0;

                for (int i=m-1;i>=0;i--)
                {
                    if (pairs.get(i).first<=n-count)
                    {
                        count+=pairs.get(i).first;
                        sum+=(pairs.get(i).first * pairs.get(i).second);
                    }
                    else
                    {
                        int c = n-count;
                        sum+=(c*pairs.get(i).second);
                        count+=c;
                    }

                    if (count == n) break;
                }

                out.write((sum+"").getBytes());

                out.flush();
            }

        }

        catch(Exception e){}
        finally{}
    }
}


//(()) () (()((()()()())))