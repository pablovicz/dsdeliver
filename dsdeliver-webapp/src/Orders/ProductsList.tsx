import { Product } from "../types";
import { checkIsSelected } from "./Helpers";
import ProductCard from "./ProductCard";

type Props = {
  products: Product[];
  selectedProducts: Product[];
  onSelectProduct: (product: Product) => void;

}


function ProductsList( {products, selectedProducts, onSelectProduct} : Props  ){
  return(
     <div className="orders-list-container">
       <div className="orders-list-items">
         {products.map(product => (
            <ProductCard 
              product={product}
              key={product.id}
              onSelectProduct={onSelectProduct}
              isSelected={checkIsSelected(selectedProducts, product)}
            />
         ))}
       </div>
     </div>

  );
};

export default ProductsList;