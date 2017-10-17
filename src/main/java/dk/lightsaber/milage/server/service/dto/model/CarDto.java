package dk.lightsaber.milage.server.service.dto.model;

public class CarDto extends BaseDto  {
	private long id;
	private String name;
	private String make;
	private String model;
	private String model_specific;
	private String fuel_type;
	private double nominated_mixed_milage;

	public long getId() {
		return id;
	}

	public CarDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public CarDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getMake() {
		return make;
	}

	public CarDto setMake(String make) {
		this.make = make;
		return this;
	}

	public String getModel() {
		return model;
	}

	public CarDto setModel(String model) {
		this.model = model;
		return this;
	}

	public String getModel_specific() {
		return model_specific;
	}

	public CarDto setModel_specific(String model_specific) {
		this.model_specific = model_specific;
		return this;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public CarDto setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
		return this;
	}

	public double getNominated_mixed_milage() {
		return nominated_mixed_milage;
	}

	public CarDto setNominated_mixed_milage(double nominated_mixed_milage) {
		this.nominated_mixed_milage = nominated_mixed_milage;
		return this;
	}

}