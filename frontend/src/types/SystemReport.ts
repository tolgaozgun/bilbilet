export type SpecificSystemReportData = {
    title: string;
    count: number;
};

export type AllSystemReport = {
	mostRentedCars: SpecificSystemReportData[];
	companyWithMostPurchasedTickets: SpecificSystemReportData[];
	mostPurchasedArrival: SpecificSystemReportData[];
	mostPurchasedDestination: SpecificSystemReportData[];
	mostExpensiveTicketOfCompany: SpecificSystemReportData[];
	cheapestTicketOfCompany: SpecificSystemReportData[];
};