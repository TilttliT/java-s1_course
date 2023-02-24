import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class TaskE {
    public static void dfs(int vertex, int[] depth, List<IntDynArray> adjacencyList, int[] parent) {
        for (int i = 0; i < adjacencyList.get(vertex).size(); ++i) {
            int nextVertex = adjacencyList.get(vertex).get(i);
            if (depth[nextVertex] == -1) {
                depth[nextVertex] = depth[vertex] + 1;
                parent[nextVertex] = vertex;
                dfs(nextVertex, depth, adjacencyList, parent);
            }
        }
    }

    public static void main(String[] args) {
        MyScanner in = new MyScanner(System.in);

        int vertexSize = in.nextInt();
        int citySize = in.nextInt();

        List<IntDynArray> adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertexSize; ++i) {
            adjacencyList.add(new IntDynArray());
        }
        for (int i = 0; i < vertexSize - 1; ++i) {
            int vertex1 = in.nextInt() - 1;
            int vertex2 = in.nextInt() - 1;

            adjacencyList.get(vertex1).add(vertex2);
            adjacencyList.get(vertex2).add(vertex1);
        }

        int[] city = new int[citySize];
        for (int i = 0; i < citySize; ++i) {
            city[i] = in.nextInt() - 1;
        }

        int[] depth = new int[vertexSize];
        Arrays.fill(depth, -1);
        depth[city[0]] = 0;
        int[] parent = new int[vertexSize];
        parent[city[0]] = -1;
        dfs(city[0], depth, adjacencyList, parent);

        int mostDepthCity = city[0];
        for (int i = 0; i < citySize; ++i) {
            if (depth[mostDepthCity] < depth[city[i]]) {
                mostDepthCity = city[i];
            }
        }

        int maxDist = depth[mostDepthCity];
        int ansVertex = -1;
        if (maxDist % 2 == 0) {
            ansVertex = mostDepthCity;
            for (int i = 0; i < maxDist / 2; ++i) {
                ansVertex = parent[ansVertex];
            }

            Arrays.fill(depth, -1);
            depth[ansVertex] = 0;
            parent[ansVertex] = -1;
            dfs(ansVertex, depth, adjacencyList, parent);

            for (int i = 0; i < citySize; ++i) {
                if (depth[city[i]] != maxDist / 2) {
                    ansVertex = -1;
                    break;
                }
            }
        }

        if (ansVertex == -1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(ansVertex + 1);
        }
    }
}

class IntDynArray {
    private int size;
    private int[] arr;

    public IntDynArray() {
        size = 0;
        arr = new int[size];
    }

    public int get(int i) {
        return arr[i];
    }

    public void set(int i, int x) {
        arr[i] = x;
    }

    public void add(int x) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, size * 3 / 2 + 1);
        }
        arr[size] = x;
        ++size;
    }

    public int size() {
        return size;
    }
}

class MyScanner {
    private final Reader in;
    private char[] buffer;
    private int bufferSize;
    private int bufferPos;
    private byte[] bytePattern;
    private int bytePatternSize;
    private char[] charPattern;
    private int charPatternSize;
    private int countOfLineSeparator;

    private MyScanner(Reader in) {
        this.in = in;
        buffer = new char[1000];
        bufferSize = 0;
        bufferPos = 0;
        bytePattern = new byte[10];
        bytePatternSize = 0;
        charPattern = new char[10];
        charPatternSize = 0;
        countOfLineSeparator = 0;
    }

    public MyScanner(String fileName, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
        this(new InputStreamReader(new FileInputStream(fileName), encoding));
    }

    public MyScanner(InputStream stream) {
        this(new InputStreamReader(stream));
    }

    public MyScanner(String inputString) {
        this(new StringReader(inputString));
    }

    private int read() {
        int res = -1;
        try {
            if (bufferPos >= bufferSize) {
                bufferSize = in.read(buffer);
                bufferPos = 0;
            }
            if (bufferSize != -1) {
                res = buffer[bufferPos++];
            }
        } catch (IOException e) {
            System.out.println("Cannot read: " + e.getMessage());
        }
        return res;
    }

    private boolean containsInPattern(char c, int type) {
        boolean res = false;
        if (type == 0) {
            res = (c != '\n' && c != '\r');
        } else if (type == 1) {
            res = Character.isDigit(c) || (c == '+' || c == '-');
        } else if (type == 2) {
            if (c == '\n') {
                countOfLineSeparator += 1;
            } else if (c == '\r') {
                int nc = read();
                if (nc == -1 || (char) nc != '\n') {
                    countOfLineSeparator += 1;
                }
                --bufferPos;
            }
            for (int i = 0; i < charPatternSize; ++i) {
                res = res | (c == charPattern[i]);
            }
            for (int i = 0; i < bytePatternSize; ++i) {
                res = res | (Character.getType(c) == bytePattern[i]);
            }
        }

        return res;
    }

    public void addInPattern(char c) {
        addInPattern(new char[]{c});
    }

    public void addInPattern(char[] c) {
        for (char x : c) {
            if (charPatternSize + 1 == charPattern.length) {
                charPattern = Arrays.copyOf(charPattern, charPatternSize * 2);
            }
            charPattern[charPatternSize++] = x;
        }
    }

    public void addInPattern(byte c) {
        addInPattern(new byte[]{c});
    }

    public void addInPattern(byte[] c) {
        for (byte x : c) {
            if (bytePatternSize + 1 == bytePattern.length) {
                bytePattern = Arrays.copyOf(bytePattern, bytePatternSize * 2);
            }
            bytePattern[bytePatternSize++] = x;
        }
    }

    private void skip(int type) {
        boolean ok = false;
        while (!ok) {
            int c = read();
            if (c == -1) {
                return;
            }
            ok = containsInPattern((char) c, type);
        }
        --bufferPos;
    }

    private String next(boolean needSkip, int type) throws NoSuchElementException {
        StringBuilder res = new StringBuilder();
        if (needSkip) {
            skip(type);
        }
        boolean ok = true;
        while (true) {
            int c = read();
            if (c == -1 || !containsInPattern((char) c, type)) {
                --bufferPos;
                break;
            } else {
                res.append((char) c);
            }
        }

        if (bufferSize == -1 && res.length() == 0) {
            throw new NoSuchElementException("New element not found");
        }
        return res.toString();
    }

    private boolean hasNext(boolean needSkip, int type) {
        if (needSkip) {
            skip(type);
        }

        int c = read();
        --bufferPos;
        return c != -1;
    }

    public void skipLineSeparators() {
        int c1 = read();
        int c2 = read();
        if ((char) c1 != '\r' || (char) c2 != '\n') {
            --bufferPos;
        }
    }

    public String nextLine() throws NoSuchElementException {
        String res = next(false, 0);
        skipLineSeparators();
        return res;
    }

    public int nextInt() throws NoSuchElementException {
        return Integer.parseInt(next(true, 1));
    }

    public String nextWord() throws NoSuchElementException {
        return next(true, 2);
    }

    public int lineSeparators() {
        return countOfLineSeparator;
    }

    public boolean hasNextLine() {
        return hasNext(false, 0);
    }

    public boolean hasNextInt() {
        return hasNext(true, 1);
    }

    public boolean hasNextWord() {
        countOfLineSeparator = 0;
        return hasNext(true, 2);
    }

    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Cannot close scanner: " + e.getMessage());
        }
    }
}