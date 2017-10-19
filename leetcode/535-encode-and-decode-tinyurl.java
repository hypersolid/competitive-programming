public class Codec {
    public HashMap<String,String> hash = new HashMap<String,String>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String result = Integer.toString(longUrl.hashCode());
        hash.put(result,longUrl);
        return result;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return hash.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
