import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { Currency } from '@/model/currency';

export async function getAllCurrencyNames() {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/currency' + name, config);
        return response.data;
    }
    catch (error) {
        return <any>error;   
    }
}

export async function getCurrency(name: string): Promise<Currency>   { 
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/currency/' + name, config);
        return response.data;
    } catch (error) {
        return <any>error;   
    }
}