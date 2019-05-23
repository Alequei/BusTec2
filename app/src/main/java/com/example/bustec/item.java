package com.example.bustec;

public class item {
    int background;
    String profileName;
    int  profilephoto;
    String nbflollowers;

    public item() {
    }

    public item(int background, String profileName, int profilephoto, String nbflollowers) {
        this.background = background;
        this.profileName = profileName;
        this.profilephoto = profilephoto;
        this.nbflollowers = nbflollowers;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getProfilephoto() {
        return profilephoto;
    }

    public void setProfilephoto(int profilephoto) {
        this.profilephoto = profilephoto;
    }

    public String getNbflollowers() {
        return nbflollowers;
    }

    public void setNbflollowers(String nbflollowers) {
        this.nbflollowers = nbflollowers;
    }
}

