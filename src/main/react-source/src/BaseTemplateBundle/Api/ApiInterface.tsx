export interface ApiInterface {
    fetchJson(url: string, options?: any): Promise<any>;
}