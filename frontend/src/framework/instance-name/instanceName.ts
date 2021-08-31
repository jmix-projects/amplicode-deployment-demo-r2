export function instanceName(entityName?: string): (entityInstance: any) => string {
  let instanceNameResolver = (entityName != null)
    ? instanceNameResolvers[entityName]
    : defaultInstanceNameResolver;

  return (entityInstance: any) => {
    // Entity instance might not be loaded yet
    if (entityInstance == null) {
      return '';
    }

    return instanceNameResolver(entityInstance);
  };
}

const instanceNameResolvers: Record<string, ((entityInstance: any) => string)> = {
  'OwnerDTO': (entityInstance: any) => {
    const {firstName, lastName} = entityInstance;
    return `${firstName} ${lastName}`;
  }
};

function defaultInstanceNameResolver(entityInstance: any) {
  if ('name' in entityInstance) {
    return entityInstance.name;
  }
  if ('id' in entityInstance) {
    return entityInstance.id;
  }
  return JSON.stringify(entityInstance);
}