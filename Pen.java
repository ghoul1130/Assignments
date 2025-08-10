import java.util.ArrayList;
import java.util.List;


// MAIN CLASS
public class Pen {
    private final int penID;         // Unique ID - immutable after creation
    private int inkLevel;            // 0 - 100
    private String inkColor;
    private String tipType;
    private final String brand = "Parker"; // Constant brand
    private boolean isCapped;

    private static int penCount = 0;   // Tracks number of Pen objects
    private static int idGenerator = 1; // Auto-increment for penID

    //Constructors
    public Pen() {
        this(100, "blue", "ballpoint"); // Default pen
    }

    public Pen(int inkLevel, String inkColor, String tipType) {
        this.penID = idGenerator++;  // Unique ID assignment
        this.setInkLevel(inkLevel);  // Validation via setter
        this.inkColor = inkColor;
        this.tipType = tipType;
        this.isCapped = true;
        penCount++;
        System.out.println("[Pen created: " + getPenDetails() + "]");
        System.out.println();
    }

    public static int getPenCount() {
        return penCount;
    }

    public void write(String message) {
        this.capOff();
        if (isCapped) {
            System.out.println("Can't write. The cap is on!");
            return;
        }
        if (inkLevel <= 0) {
            System.out.println("Out of ink! Cannot write: '" + message + "'");
            return;
        }
        System.out.println("Writing: '" + message + "' with the " + inkColor + " pen.");
        inkLevel -= message.length();
        this.capOn();
    }

    public void changeColor(String newColor) {
        System.out.println("Changing color from " + inkColor + " to " + newColor + ".");
        this.inkColor = newColor;
    }

    public void capOn() {
        System.out.println("Click! Capping the pen.");
        isCapped = true;
    }

    public void capOff() {
        System.out.println("Click! Uncapping the pen.");
        isCapped = false;
    }

    public void refillInk(int amount) {
        if (amount <= 0) {
            System.out.println("Refill amount must be positive.");
            return;
        }
        inkLevel = Math.min(100, inkLevel + amount);
        System.out.println("Ink refilled. Current ink level: " + inkLevel + "%");
    }

    public String getPenDetails() {
        return "PenID: " + penID + ", Color: " + inkColor + ", Tip: " + tipType +
                ", Brand: " + brand + ", InkLevel: " + inkLevel + "%, Capped: " + isCapped;
    }

    public int getInkLevel() {
        return inkLevel;
    }

    public void setInkLevel(int inkLevel) {
        if (inkLevel >= 0 && inkLevel <= 100) {
            this.inkLevel = inkLevel;
        } else {
            System.out.println("Invalid ink level! Must be between 0-100.");
        }
    }

    public String getInkColor() {
        return inkColor;
    }

    public void setInkColor(String inkColor) {
        this.inkColor = inkColor;
    }

    public String getTipType() {
        return tipType;
    }

    public void setTipType(String tipType) {
        this.tipType = tipType;
    }

    public int getPenID() {
        return penID;
    }

    public final void checkInkLevel() {
        System.out.println("Ink level: " + inkLevel + "%");
    }
}


// SUBCLASS - Inheritance Example

class FountainPen extends Pen {
    private int nibSize;

    public FountainPen(int inkLevel, String inkColor, String tipType, int nibSize) {
        super(inkLevel, inkColor, tipType);
        this.nibSize = nibSize;
    }

    public int getNibSize() {
        return nibSize;
    }

    public void setNibSize(int nibSize) {
        this.nibSize = nibSize;
    }
}

class TestDemo {
    public static void main(String[] args) {
        Pen p1 = new Pen(80, "green", "ballpoint");

        p1.write("Hello World");
        p1.checkInkLevel();
        p1.refillInk(30);
        p1.changeColor("red");

        FountainPen fp1 = new FountainPen(90, "blue", "fountain", 5);
        fp1.write("Elegant writing");

        PenBox box = new PenBox();
        box.addPen(p1);
        box.addPen(fp1);
        box.listAllPens();
        box.findPenByColor("red");
        box.removePen(0);

        System.out.println("Total pens created: " + Pen.getPenCount());
    }
}

OUTPUT

[Pen created: PenID: 1, Color: green, Tip: ballpoint, Brand: Parker, InkLevel: 80%, Capped: true]

Click! Uncapping the pen.
Writing: 'Hello World' with the green pen.
Click! Capping the pen.
Ink level: 69%
Ink refilled. Current ink level: 99%
Changing color from green to red.
[Pen created: PenID: 2, Color: blue, Tip: fountain, Brand: Parker, InkLevel: 90%, Capped: true]

Click! Uncapping the pen.
Writing: 'Elegant writing' with the blue pen.
Click! Capping the pen.
Pen added to box: PenID: 1, Color: red, Tip: ballpoint, Brand: Parker, InkLevel: 99%, Capped: true

Pen added to box: PenID: 2, Color: blue, Tip: fountain, Brand: Parker, InkLevel: 75%, Capped: true

Listing all pens:
PenID: 1, Color: red, Tip: ballpoint, Brand: Parker, InkLevel: 99%, Capped: true
PenID: 2, Color: blue, Tip: fountain, Brand: Parker, InkLevel: 75%, Capped: true
Found pen: PenID: 1, Color: red, Tip: ballpoint, Brand: Parker, InkLevel: 99%, Capped: true
Total pens created: 2
