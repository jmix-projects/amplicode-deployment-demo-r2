import {CloseOutlined} from "@ant-design/icons";

interface Props {
  caption: string;
  onClose: () => void;
}

export const TabHeading = ({caption, onClose}: Props) => {
  return (
    <span>
      {caption}
      &nbsp;
      <CloseOutlined onClick={onClose}
                     style={{marginRight: 0}}
      />
    </span>
  );
}