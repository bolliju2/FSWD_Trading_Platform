export interface Currency {
    name: string;
    historicalValues: CurrencyValue[]
}

export interface CurrencyValue {
    timestamp: string;
    value: number;
}