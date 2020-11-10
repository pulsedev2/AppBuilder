package fr.pulsedev.appbuilder.visualeditor;

public class Tag<A> {
    private final String NAME;
    private A value;
    private Class<A> type;

    public Tag(String NAME, A defaultValue, Class<A> type) {
        this.NAME = NAME;
        this.value = defaultValue;
        this.type = type;
    }

    public String getName(){
        return this.NAME;
    }

    public A getValue() {
        return value;
    }

    public void setValue(Object value) {
        if(value.getClass().isInstance(type)){
            this.value = (A) value;
        }
    }

    public Class<A> getType(){
        return this.type;
    }
}
