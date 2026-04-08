
export const TabValues = {
  CATEGORIA: "CATEGORIA",
  TAG: "TAG"
} as const;

export type TabValues = typeof TabValues[keyof typeof TabValues];