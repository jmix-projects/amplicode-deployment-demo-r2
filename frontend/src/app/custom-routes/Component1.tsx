import {Image} from 'antd';
import {useEffect} from "react";

export const Component1 = () => {
  useEffect(() => {
    document.title = 'Jmix2 Petclinic / Component1';
  }, []);

  return (
    <div>
      <Image
        width={200}
        src="https://gw.alipayobjects.com/zos/antfincdn/aPkFc8Sj7n/method-draw-image.svg"
      />
      <p>Component 1</p>
    </div>
  );
};