export type PaymentType = 'balance' | 'credit-card' | null;
export enum TransactionType {
	WITHDRAW = 'WITHDRAW',
	REFUND = 'REFUND',
	BUY_TICKET_WITH_CARD = 'BUY_TICKET_WITH_CARD',
	BUY_TICKET_WITH_BALANCE = 'BUY_TICKET_WITH_BALANCE',
	ADD_FUNDS = 'ADD_FUNDS',
	TRANSFER = 'TRANSFER',
	OTHER = 'OTHER',
}

export interface Transaction {
	transactionId: number;
	transactionType: TransactionType;
	transactionAmount: number;
	receiverId: number;
	senderId: number;
}

export interface PaymentWithCCRequest {
	travelerId: number;
	creditCard: CreditCard;
	amount: number;
}

export interface CreditCard {
	cardNumber: number;
	cardHolderName: string;
	expirationMonth: number;
	expirationYear: number;
	cvv: number;
}
