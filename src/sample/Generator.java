package sample;

public class Generator {
    private static String currentImg = "";
    private static String translationF;

    public static String Read(int systemInt)
    {
        for(int n = 0; n < systemInt; n++)
        {
            StringBuffer next = new StringBuffer();
            for (int i = 0; i < currentImg.length(); i++)
            {
                char current = currentImg.charAt(i);

                if (current == 'F')
                    next.append(translationF);
            }
            currentImg = next.toString();
        }
        return currentImg;
    }

    public static void Start(String current, String translationFF)
    {
        currentImg = current;
        translationF = translationFF;
    }
}
