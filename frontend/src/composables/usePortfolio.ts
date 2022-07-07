import { onMounted, ref } from "vue";
import { Portfolio } from "@/model/portfolio";
import { getPortfolios } from "@/api/portfolios";

export function usePortfolio() {

    const portfolio = ref<Portfolio>({});

    const getPortfolio = async () => {
        try {
            portfolio.value = await getPortfolios();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getPortfolio);
    setInterval(getPortfolio, 1000);

    return {
        portfolio,
    }
}