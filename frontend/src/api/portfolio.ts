import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { ToDo } from '@/model/todo';
import { Portfolio, Transaction } from '@/model/portfolio';

export async function getPortfolio(): Promise<Portfolio>   { 
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/portfolio', config);
        return response.data;
    } catch (error) {
        return <any>error;   
    }
}

export async function postTransaction(transaction: Transaction): Promise<Transaction> {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.post(API_ROOT + '/api/transaction', transaction, config);
        return response.data;
    } catch (error) {
        return <any>error;   
    }
}