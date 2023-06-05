package edu.bilkent.bilbilet.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RFareDetailsView {
    @NotNull
	private Integer fareId;

	@NotNull
	private Timestamp depTime;

	@NotNull
	private Timestamp arrTime;

	@NotNull
	private BigDecimal basePrice;
	
	@NotNull
	private BigDecimal premiumEconExtraPrice;
	
	@NotNull
	private BigDecimal businessExtraPrice;
	
    @NotNull
    private BigDecimal firstClassExtraPrice;

    @NotNull
    private BigDecimal reservationFee;

    @NotBlank
    private String arrStationTitle;

    @NotBlank
    private String arrStationAbbr;

    @NotBlank
    private String depStationTitle;

    @NotBlank
    private String depStationAbbr;

    @NotBlank
    private String companyName;
}