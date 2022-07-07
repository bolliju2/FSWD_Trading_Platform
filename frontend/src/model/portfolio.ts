//import { Transaction } from "@/model/transaction";


export interface Portfolio {
    id?: number;
    cash?: number;
    transactions?: Transaction[]
}



export interface Transaction {
    id?: number;
    date?: string;
    exchangePrice?: number;
    amountCoins?: number;
}
