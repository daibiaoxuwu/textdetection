public class TestEncoding {


    public static String getEncoding(String str) {

        String encode = "GB2312";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s = encode;

                return s;

            }

        } catch (Exception exception) {

            exception.printStackTrace();

        }

        encode = "ISO-8859-1";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s1 = encode;

                return s1;

            }

        } catch (Exception exception1) {

            exception1.printStackTrace();

        }

        encode = "UTF-8";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s2 = encode;

                return s2;

            }

        } catch (Exception exception2) {

            exception2.printStackTrace();

        }

        encode = "GBK";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s3 = encode;

                return s3;

            }

        } catch (Exception exception3) {

            exception3.printStackTrace();

        }

        encode = "ASCII";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s3 = encode;

                return s3;

            }

        } catch (Exception exception3) {

            exception3.printStackTrace();

        }

        encode = "GB18030";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s3 = encode;

                return s3;

            }

        } catch (Exception exception3) {

            exception3.printStackTrace();

        }

        encode = "Unicode";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s3 = encode;

                return s3;

            }

        } catch (Exception exception3) {

            exception3.printStackTrace();

        }

        encode = "Shift_JIS";

        try {

            if (str.equals(new String(str.getBytes(encode), encode))) {

                String s3 = encode;

                return s3;

            }

        } catch (Exception exception3) {

            exception3.printStackTrace();

        }

        return "";

    }
}
