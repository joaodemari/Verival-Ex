public class KingsDayDiscount {

    private Calendar today;

    public KingsDayDiscount(Calendar _today){
        this.today = _today;
    }
    public double discount(double value) {
    boolean isKingsDay = this.today.get(MONTH) == Calendar.APRIL
    && this.today.get(DAY_OF_MONTH) == 27;
    return isKingsDay ? value * 0.15 : 0;
    }
}
   