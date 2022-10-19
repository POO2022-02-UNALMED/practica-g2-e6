package gestorAplicacion.economia;

public enum Divisa {
    EURO(4000,4500),
    PESO(1,1),
    DOLAR(4200,5000);

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
