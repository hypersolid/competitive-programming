static class FastWriter {
    private final StringBuilder out;

    public FastWriter() {
        out = new StringBuilder(Solution.IO_BUFFERS);
    }

    public FastWriter print(Object object) {
        out.append(object);
        return this;
    }

    public FastWriter print(String format, Object... args) {
        out.append(String.format(format, args));
        return this;
    }

    public FastWriter println(Object object) {
        out.append(object).append("\n");
        return this;
    }

    public void close() throws IOException {
        System.out.print(out);
    }
}

static class FastReader {
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public FastReader() {
        din = new DataInputStream(System.in);
        buffer = new byte[Solution.IO_BUFFERS];
        bufferPointer = bytesRead = 0;
    }

    public FastReader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[Solution.IO_BUFFERS];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                if (sb.length() > 0) break;
                else continue;
            }
            sb.append((char) c);
        }
        return sb.toString();
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) return -ret;
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg) return -ret;
        return ret;
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, Solution.IO_BUFFERS);
        if (bytesRead == -1) buffer[0] = -1;
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead) fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) return;
        din.close();
    }

    int[] fillIntegerArray(int n) throws IOException {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = nextInt();
        return array;
    }

    long[] fillLongArray(int n) throws IOException {
        long[] array = new long[n];
        for (int i = 0; i < n; i++) array[i] = nextLong();
        return array;
    }
}

