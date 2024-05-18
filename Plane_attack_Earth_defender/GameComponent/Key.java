package GameComponent;

public class Key 
{
    private boolean key_right;
    private boolean key_left;
    private boolean key_up;
    private boolean key_down;
    private boolean key_l;
    private boolean key_k;  
    private boolean key_enter;

    public boolean isKey_enter() {
        return key_enter;
    }
    public void setKey_enter(boolean key_enter) {
        this.key_enter = key_enter;
    }
    /**
     * @return boolean return the key_right
     */
    public boolean isKey_down() {
        return key_down;
    }
    public void setKey_down(boolean key_down) {
        this.key_down = key_down;
    }
    public boolean isKey_right() {
        return key_right;
    }

    /**
     * @param key_right the key_right to set
     */
    public void setKey_right(boolean key_right) {
        this.key_right = key_right;
    }

    /**
     * @return boolean return the key_left
     */
    public boolean isKey_left() {
        return key_left;
    }

    /**
     * @param key_left the key_left to set
     */
    public void setKey_left(boolean key_left) {
        this.key_left = key_left;
    }

    /**
     * @return boolean return the key_space
     */
    public boolean isKey_up() {
        return key_up;
    }

    /**
     * @param key_space the key_space to set
     */
    public void setKey_up(boolean key_up) {
        this.key_up = key_up;
    }

    /**
     * @return boolean return the key_j
     */
    public boolean isKey_l() {
        return key_l;
    }

    /**
     * @param key_j the key_j to set
     */
    public void setKey_l(boolean key_l) {
        this.key_l = key_l;
    }

    /**
     * @return boolean return the key_k
     */
    public boolean isKey_k() {
        return key_k;
    }

    /**
     * @param key_k the key_k to set
     */
    public void setKey_k(boolean key_k) {
        this.key_k = key_k;
    }

}
