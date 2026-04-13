import { useEffect, useState } from "react";
import useApi from "../../../../../core/api/hooks/use-api";
import type { CategoryMetrics } from "../models/categories-metrics";
import type { TagMetrics } from "../models/tag-metrics";
import getCategoryMetrics from "../services/category-metrics/category-metrics.service";
import getTagMetrics from "../services/tag-metrics/tag-metrics.service";

const useMetrics = () => {

    const [categoriesMetrics, setCategoriesMetrics] = useState<CategoryMetrics[]>([]);
    const [tagsMetrics, setTagsMetrics] = useState<TagMetrics[]>([]);
    const {get} = useApi();

    const getters = () => [
        get(getCategoryMetrics)
            .then(categoriesMetrics => setCategoriesMetrics(categoriesMetrics))
            .catch(error => console.error(error)),
        get(getTagMetrics)
            .then(tagsMetrics => setTagsMetrics(tagsMetrics))
            .catch(error => console.error(error))
    ];

    useEffect(() => {
        getters();
    }, []);

    return {categoriesMetrics, tagsMetrics}
}

export default useMetrics;