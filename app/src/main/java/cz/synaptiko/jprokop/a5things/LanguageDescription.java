package cz.synaptiko.jprokop.a5things;

/**
 * Data structure for data about a language.
 */
public class LanguageDescription {

    private String mName;
    private int mLogoDrawableResource;
    private String mTryItUrl;
    private String mWikipediaUrl;
    private String mExampleCode;

    /**
     * Creates new language description.
     *
     * @param name Name of the language
     * @param logoDrawableResource Reference to drawable resource
     * @param tryItUrl Url with an example code or REPL
     * @param wikipediaUrl Url with wikipedia page about the language
     * @param exampleCode An example code specific to the language
     */
    public LanguageDescription(String name, int logoDrawableResource, String tryItUrl, String wikipediaUrl, String exampleCode) {
        mName = name;
        mLogoDrawableResource = logoDrawableResource;
        mTryItUrl = tryItUrl;
        mWikipediaUrl = wikipediaUrl;
        mExampleCode = exampleCode;
    }

    public String getName() {
        return mName;
    }

    public int getLogoDrawableResource() {
        return mLogoDrawableResource;
    }

    public String getTryItUrl() {
        return mTryItUrl;
    }

    public String getWikipediaUrl() {
        return mWikipediaUrl;
    }

    public String getExampleCode() {
        return mExampleCode;
    }

}
