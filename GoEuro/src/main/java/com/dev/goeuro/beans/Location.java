package com.dev.goeuro.beans;



//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Location {
	
	private static final int NUM_CSV_PARAMS = 6;
	
	//private String _type;
	private String _id;
	private String key;
	private String name;
	
	private String fullName;
    private String iata_airport_code;
    private String type;
    private String country;
	private GeoPosition geo_position;
	private Long locationId;
    private Boolean inEurope;
    private String countryCode;
    private Boolean coreCountry;
    private String distance;
    public Location() {
    }

    public Location(String _id, String key, String name, String fullName, String iata_airport_code, String type, String country, GeoPosition geo_position, Long locationId, Boolean inEurope, String countryCode, Boolean coreCountry, String distance) {
        this._id = _id;
        this.key = key;
        this.name = name;
        this.fullName = fullName;
        this.iata_airport_code = iata_airport_code;
        this.type = type;
        this.country = country;
        this.geo_position = geo_position;
        this.locationId = locationId;
        this.inEurope = inEurope;
        this.countryCode = countryCode;
        this.coreCountry = coreCountry;
     
    }
	public String[] toCSV()
	{
		String [] csv_array = new String[NUM_CSV_PARAMS];
		
		int index = 0;
		//csv_array[index++] = _type;
		csv_array[index++] = _id;
		csv_array[index++] = name;
		csv_array[index++] = type;
		csv_array[index++] = geo_position != null ? geo_position.getLatitude() : "";
		csv_array[index++] = geo_position != null ? geo_position.getLongitude(): "";
		return csv_array;
	}
	
	/* @JsonProperty("_type")
	String get_Type() 
	{
		return _type;
	}

	@JsonProperty("_type")
	void set_Type(String _type) 
	{
		this._type = _type;
	}*/

	@JsonProperty("_id")
	String get_Id() 
	{
		return _id;
	}

	@JsonProperty("_id")
	void set_Id(String _id) 
	{
		this._id = _id;
	}
	@JsonProperty("name")
	String getName() 
	{
		return name;
	}
	@JsonProperty("name")
	void setName(String name)
	{
		this.name = name;
	}
	
	@JsonProperty("type")
	String getType() 
	{
		return type;
	}
	@JsonProperty("type")
	void setType(String type) 
	{
		this.type = type;
	}

	@JsonProperty	("geo_position")
	private GeoPosition getGeo_position() 
	{
		return geo_position;
	}

	@JsonProperty("geo_position")
	private void setGeo_position(GeoPosition geo_position) 
	{
		this.geo_position = geo_position;
	}
	public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getIata_airport_code() {
        return iata_airport_code;
    }

    public void setIata_airport_code(String iata_airport_code) {
        this.iata_airport_code = iata_airport_code;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Boolean getInEurope() {
        return inEurope;
    }

    public void setInEurope(Boolean inEurope) {
        this.inEurope = inEurope;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Boolean getCoreCountry() {
        return coreCountry;
    }

    public void setCoreCountry(Boolean coreCountry) {
        this.coreCountry = coreCountry;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

	


}
