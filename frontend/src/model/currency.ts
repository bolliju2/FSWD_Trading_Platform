export interface Currency {
    symbol: string;
    historicalValues: CurrencyValue[]
}

export interface CurrencyValue {
    timestamp: string;
    value: number;
}