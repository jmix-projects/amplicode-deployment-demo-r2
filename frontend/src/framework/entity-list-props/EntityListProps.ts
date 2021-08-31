export interface EntityListProps {
  mode?: 'crud' | 'select';
  onSelect?: (entityInstance: any) => void;
}