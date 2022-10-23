package gestorAplicacion.economia;

public enum Divisa {
    EUR(4000,4500),
    COP(1,1),
    USD(4200,5000),
    JPY(30,34),
    GBP(4500, 5500),
    AUD(2800, 3400),
    CAD(3200,3600),
    CHF(4400,5200),
    CNH(610,680),
    HKD(550,630),
    NZD(2500, 2900),
    MXN(210, 250),
    VND(0.18, 0.22);
    private double minValue;
    private double maxValue;

    Divisa(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
    public double[] ConvertToDivisa(double value, Divisa target){
        double[] result = new double[2];
        if (target == this) {
            result[0] = value;
            result[1] = 1;
            return result;
        };
        result[1] = (Math.random()*(target.getMaxValue()-target.getMinValue()) + target.getMinValue())/(Math.random()*(this.getMaxValue()-this.getMinValue()) + this.getMinValue());
        result[0] = value / result[1];
        return result;
    }
}
