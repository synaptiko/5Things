package cz.synaptiko.jprokop.a5things;

public class LanguageDescription {

    private String mName;
    private int mLogoDrawableResource;
    private String mTryItUrl;
    private String mWikipediaUrl;
    private String mExampleCode;

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
