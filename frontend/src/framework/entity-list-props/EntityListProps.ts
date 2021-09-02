export type EntityListMode = 'crud' | 'readOnly' | 'select';

export interface EntityListProps {
  onSelect?: (entityInstance: any) => void;
}