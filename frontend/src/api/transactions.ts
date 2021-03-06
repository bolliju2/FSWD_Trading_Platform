import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { Transaction } from '@/model/transaction';

export async function getAllTransactions(): Promise<Transaction[]>   { 
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/transaction', config);
        console.log(response.data)
        return response.data;
        
    } catch (error) {
        return <any>error;   
    }
}

export async function addNewTransaction(newTransaction: Transaction): Promise<any> {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.post(API_ROOT + '/api/transaction', newTransaction, config);
        console.log(response.data)
        return response.data;
        
    } catch (error) {
        return <any>error;   
    }
}