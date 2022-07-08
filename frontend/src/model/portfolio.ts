import { Transaction } from "@/model/transaction";

export interface Portfolio {
    id?: number;
    cash?: number;
    transactions?: Transaction[]
}