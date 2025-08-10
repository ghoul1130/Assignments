import java.util.ArrayList;
import java.util.List;

class PenBox {
    private List<Pen> pens = new ArrayList<>();

    public void addPen(Pen pen) {
        pens.add(pen);
        System.out.println("Pen added to box: " + pen.getPenDetails());
        System.out.println();
    }

    public void removePen(int index) {
        if (index >= 0 && index < pens.size()) {
            pens.remove(index);
        } else {
            System.out.println("Invalid index!");
        }
    }

    public void listAllPens() {
        if (pens.isEmpty()) {
            System.out.println("No pens in the box.");
            return;
        }
        System.out.println("Listing all pens:");
        for (Pen pen : pens) {
            System.out.println(pen.getPenDetails());
        }
    }

    public void findPenByColor(String color) {
        boolean found = false;
        for (Pen pen : pens) {
            if (pen.getInkColor().equalsIgnoreCase(color)) {
                System.out.println("Found pen: " + pen.getPenDetails());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pen found with color: " + color);
        }
    }
}
