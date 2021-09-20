import {Image} from 'antd';
import {useEffect} from "react";

export const Component2 = () => {
  useEffect(() => {
    document.title = 'Jmix2 Petclinic / Component2';
  }, []);

  return (
    <div>
      <Image
        width={200}
        src="https://gw.alipayobjects.com/zos/rmsportal/KDpgvguMpGfqaHPjicRK.svg"
      />
      <p>Component 2</p>
    </div>
  );
};