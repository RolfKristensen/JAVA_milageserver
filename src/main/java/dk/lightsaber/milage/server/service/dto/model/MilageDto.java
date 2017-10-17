package dk.lightsaber.milage.server.service.dto.model;

public class MilageDto {
	private int id;
	private float litresFuled;
	private float kmDriven;
	private float kmL;
	private float priceL;
	private float priceKm;
	private float priceSum;
	private int gasTypeId;
	private int gasStationId;
	private int userId;
	private int carId;

	public int getCarId() {
		return carId;
	}

	public int getGasStationId() {
		return gasStationId;
	}

	public int getGasTypeId() {
		return gasTypeId;
	}

	public int getId() {
		return id;
	}

	public float getKmDriven() {
		return kmDriven;
	}

	public float getKmL() {
		return kmL;
	}

	public float getLitresFuled() {
		return litresFuled;
	}

	public float getPriceKm() {
		return priceKm;
	}

	public float getPriceL() {
		return priceL;
	}

	public float getPriceSum() {
		return priceSum;
	}

	public int getUserId() {
		return userId;
	}

	public MilageDto setCarId(int carId) {
		this.carId = carId;
		return this;
	}

	public MilageDto setGasStationId(int gasStationId) {
		this.gasStationId = gasStationId;
		return this;
	}

	public MilageDto setGasTypeId(int gasTypeId) {
		this.gasTypeId = gasTypeId;
		return this;
	}

	public MilageDto setId(int id) {
		this.id = id;
		return this;
	}

	public MilageDto setKmDriven(float kmDriven) {
		this.kmDriven = kmDriven;
		return this;
	}

	public MilageDto setKmL(float kmL) {
		this.kmL = kmL;
		return this;
	}

	public MilageDto setLitresFuled(float litresFuled) {
		this.litresFuled = litresFuled;
		return this;
	}

	public MilageDto setpriceKm(float priceKm) {
		this.priceKm = priceKm;
		return this;
	}

	public MilageDto setPriceL(float priceL) {
		this.priceL = priceL;
		return this;
	}

	public MilageDto setPriceSum(float priceSum) {
		this.priceSum = priceSum;
		return this;
	}

	public MilageDto setUserId(int userId) {
		this.userId = userId;
		return this;
	}
}