import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { Portfolio } from '@/model/portfolio';

export async function getPortfolios(): Promise<Portfolio>   { 
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