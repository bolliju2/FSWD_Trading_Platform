export interface Transaction {
    transactionId?: number;
    exchangePrice?: number;
    amountCoins?: number;
    symbol?: string;
    buy?: boolean;
}